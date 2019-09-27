#create bucket
aws s3 mb s3://consumer-profile-lambda-bucket

#copy the jar file to the S3 bucket
aws cloudformation package --template-file sam.yaml --output-template-file target/output-sam.yaml --s3-bucket consumer-profile-lambda-bucket

#deploy a cloud-formation stack from the SAM template
aws cloudformation deploy --template-file target/output-sam.yaml --stack-name consumer-profile-api --capabilities CAPABILITY_IAM

#describe the stack, which will display the URL of the API in the outputs.
aws cloudformation describe-stacks --stack-name consumer-profile-api