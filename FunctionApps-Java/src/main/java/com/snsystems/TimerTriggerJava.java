package com.snsystems;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import com.snsystems.models.EmailAlert;
import com.snsystems.util.DBHelper;
import com.snsystems.util.StorageHelper;

/**
 * Azure Functions with Timer trigger.
 */
public class TimerTriggerJava {
    
    /**
     * This function will be invoked periodically according to the specified schedule.
     */
    @FunctionName("TimerTriggerJava1")
    public void run(
        @TimerTrigger(name = "timerInfo", schedule = "0 */5 * * * *") String timerInfo,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Timer trigger function Starting execution at: " + LocalDateTime.now());

        Connection connection = DBHelper.getConnection("email-alert-db-conn-url");
        
        context.getLogger().info(" EmailAlerts ... Before processing ... ");
        
        String selectSql = "SELECT * from EMAIL_ALERT where IS_EMAIL_SENT = 0";
        List<EmailAlert> emailAlerts = DBHelper.executeQuery(connection, selectSql, true);
        // DBUtil.fetchEmailAlerts(selectResultSet);
        /// uploadBlob(context, emailAlerts);
        
        context.getLogger().info("Processing EmailAlerts ...");
        String updateSql = "UPDATE EMAIL_ALERT SET IS_EMAIL_SENT = 1 where IS_EMAIL_SENT = 0";
        DBHelper.executeQuery(connection, updateSql, false);

        String selectSqlAll = "SELECT * from EMAIL_ALERT";
        emailAlerts = DBHelper.executeQuery(connection, selectSqlAll, true);
        // emailAlerts = DBUtil.fetchEmailAlerts(selectResultSetAll);
        uploadBlob(context, emailAlerts);

        context.getLogger().info(" EmailAlerts ... After processing ... ");
        context.getLogger().info("Java Timer trigger function executed at: " + LocalDateTime.now());

    }
    
    void display(ExecutionContext context, List<EmailAlert> emailAlerts) {

        context.getLogger().info(" EmailAlerts ... display ... ");

        for (EmailAlert emailAlert : emailAlerts) {
          	 try {
          		context.getLogger().info(new ObjectMapper().writeValueAsString(emailAlert));
   			} catch (JsonProcessingException e) {
   				e.printStackTrace();
   			}
          }
    }
    
    public void uploadBlob(ExecutionContext context, List<EmailAlert> emailAlerts) {

        display(context, emailAlerts);

        context.getLogger().info(" EmailAlerts ... uploadBlob ... ");

		BlobServiceClient blobServiceClient = StorageHelper.accessBlobServiceClient(
		        "sathyafuncstoreaccc",
                "5JoAGKSiZmewambZSHGcqrNWM");
//    	 BlobServiceClient blobServiceClient = StorageHelper.accessBlobServiceClient(
//             "EMAIL_ALERTS_STORAGE_ACCOUNT_NAME",
//             "EMAIL_ALERTS_STORAGE_ACCOUNT_KEY");
		BlobContainerClient blobContainerClient = StorageHelper.getBlobContainer(blobServiceClient, "email-alerts");
    	
        for (EmailAlert emailAlert : emailAlerts) {
          	 try {
          		 String message = new ObjectMapper().writeValueAsString(emailAlert);
           		context.getLogger().info(message);
         		StorageHelper.uploadBlob(blobContainerClient, emailAlert.getId()+".txt", message);
   			} catch (JsonProcessingException e) {
   				e.printStackTrace();
   			} catch (IOException e) {
				e.printStackTrace();
			}
          }

		StorageHelper.listBlob(blobContainerClient);

    }
    
 }
