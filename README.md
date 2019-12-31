# PoC Data Processor


## Build
1. mvn clean package
1. mvn dockerfile:build
1. docker container run -it -p8080:8080 -e AWS_ACCESS_KEY_ID=mykey -e AWS_SECRET_ACCESS_KEY=mysecret adr1/poc-data-processor
1. docker tag adr1/poc-data-processor:latest ***REMOVED***.dkr.ecr.ap-southeast-1.amazonaws.com/poc-data-processor:latest
1. $(aws ecr get-login --no-include-email --region ap-southeast-1)
1. docker push ***REMOVED***.dkr.ecr.ap-southeast-1.amazonaws.com/poc-data-processor:latest
