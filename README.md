# PoC Data Processor


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
