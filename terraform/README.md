 az login --tenant <TENANT>
 
 az ad sp create-for-rbac --role="Contributor" --scopes="/subscriptions/<SUBSCRIPTION>"


export ARM_CLIENT_ID="xxxxxxxxxx"
export ARM_CLIENT_SECRET="xxxxxxxxxxx"
export ARM_SUBSCRIPTION_ID="xxxxxxxxxxxx"
export ARM_TENANT_ID="xxxxxxxxxxx"


## Override variable
terraform apply -var "resource_group_name=myNewResourceGroupName"

## useful links
https://developer.hashicorp.com/terraform/tutorials/configuration-language/variables
https://developer.hashicorp.com/terraform/tutorials/azure-get-started/azure-outputs