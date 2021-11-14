
echo "Welcome to EmailAlerts requests script"
for i in 100; do
	echo "Request: "$i
	curl -X GET "https://sathya-emailalerts-app-docker4.azurewebsites.net/api/v1/emailAlerts" -H "accept: application/json"
done

for (( i = 0; i < 1000; i++ )); do
	echo "Request: "$i
	curl -X GET "https://sathya-emailalerts-app-docker4.azurewebsites.net/api/v1/emailAlerts" -H "accept: application/json"
done