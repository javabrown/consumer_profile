#delete stack
aws cloudformation delete-stack --stack-name consumer-profile-api

# better handling needed.
aws s3 rb s3://consumer-profile-lambda-bucket --force