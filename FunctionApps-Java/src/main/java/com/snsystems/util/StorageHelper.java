package com.snsystems.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import com.azure.core.util.Configuration;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.azure.storage.common.StorageSharedKeyCredential;


public class StorageHelper {
	
	static String getAccountName(String storageAccNameEnv) {
        System.out.println("getAccountName");
		return Configuration.getGlobalConfiguration().get(storageAccNameEnv);
	}

	static String getAccountKey(String storageAccKeyEnv) {
        System.out.println("getAccountKey");
		return Configuration.getGlobalConfiguration().get(storageAccKeyEnv);
	}
	
    /**
     * Entry point into the basic examples for Storage blobs.
     *
     * @param args Unused. Arguments to the program.
     * @throws IOException If an I/O error occurs
     * @throws RuntimeException If the downloaded data doesn't match the uploaded data
     */
    public static BlobServiceClient accessBlobServiceClient(String accountName, String accountKey) {

        System.out.println("accessBlobServiceClient");
        /*
         * From the Azure portal, get your Storage account's name and account key.
         */
        // String accountName = getAccountName(accNameEnv);
        // String accountKey = getAccountKey(accKeyEnv);

        /*
         * Use your Storage account's name and key to create a credential object; this is used to access your account.
         */
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);

        /*
         * From the Azure portal, get your Storage account blob service URL endpoint.
         * The URL typically looks like this:
         */
        String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", accountName);

        /*
         * Create a BlobServiceClient object that wraps the service endpoint, credential and a request pipeline.
         */
        BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();


        return storageClient;


    }
    
    public static BlobContainerClient createBlobContainer(BlobServiceClient storageClient, String containerName) {
    	
        System.out.println("createBlobContainer");
        /*
         * Create a client that references a to-be-created container in your Azure Storage account. This returns a
         * ContainerClient object that wraps the container's endpoint, credential and a request pipeline (inherited from storageClient).
         * Note that container names require lowercase.
         */
        BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient(containerName);

        /*
         * Create a container in Storage blob account.
         */
        blobContainerClient.create();

        return blobContainerClient;
    	
    }

    public static BlobContainerClient getBlobContainer(BlobServiceClient storageClient, String containerName) {
    	
        System.out.println("getBlobContainer");
        /*
         * Create a client that references a to-be-created container in your Azure Storage account. This returns a
         * ContainerClient object that wraps the container's endpoint, credential and a request pipeline (inherited from storageClient).
         * Note that container names require lowercase.
         */
        BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient(containerName);


        return blobContainerClient;
    	
    }
    
    public static BlockBlobClient uploadBlob(BlobContainerClient blobContainerClient, String blobName, String data) throws IOException{
    	
        System.out.println("uploadBlob");
        /*
         * Create a client that references a to-be-created blob in your Azure Storage account's container.
         * This returns a BlockBlobClient object that wraps the blob's endpoint, credential and a request pipeline
         * (inherited from containerClient). Note that blob names can be mixed case.
         */
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient(blobName).getBlockBlobClient();

        InputStream dataStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));

//      BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
//      InputStream is = new ByteArrayInputStream(textNew.getBytes());
//      blobClient.upload(is, textNew.length());        

        
        if (!blockBlobClient.exists()) {
            /*
             * Create the blob with string (plain text) content.
             */
            blockBlobClient.upload(dataStream, data.length());

            dataStream.close();
        }
        
        return blockBlobClient;
        
    }
    
    public static void listBlob(BlobContainerClient blobContainerClient) {
        /*
         * List the blob(s) in our container.
         */
        blobContainerClient.listBlobs()
            .forEach(blobItem -> System.out.println("Blob name: " + blobItem.getName() + ", Snapshot: " + blobItem.getSnapshot()));

    }
    
    public static void cleanupBlobAndContainer(BlobClient blobClient, BlobContainerClient blobContainerClient) {
    	
        /*
         * Delete the blob we created earlier.
         */
        blobClient.delete();

        /* 
         * Delete the container we created earlier.
         */
        blobContainerClient.delete();
        
    }
    
 }
