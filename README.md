# PoC Data Processor
This is a simple data processor demo application using Spring Boot and Spring Cloud to access AWS SQS
and DynamoDB.

You can read more details about this in my blog post at
http://code.eidorian.com/aws/2019/12/17/aws-poc-data-feed-serverless.html. It is a four-part post for serverless
webhooks using AWS Lambda.

## Build and run
Build the application

`mvn clean package`

Test locally the jar

`java -jar target/poc-data-processor-1.0-SNAPSHOT.jar`

Build the Docker image

`mvn dockerfile:build`

Run and test the container locally
`docker container run -it -e AWS_ACCESS_KEY_ID=mykey -e AWS_SECRET_ACCESS_KEY=mysecret adr1/poc-data-processor`

Tag the container to your AWS account's ECR
`docker tag adr1/poc-data-processor:latest myaccount.dkr.ecr.myregion.amazonaws.com/poc-data-processor:latest`

Login to your AWS account
`$(aws ecr get-login --no-include-email --region ap-southeast-1)`

Push the image to your AWS account's ECR
`docker push myaccount.dkr.ecr.myregion.amazonaws.com/poc-data-processor:latest`
