variable "region" {
  description = "region"
  type        = string
  default     = "westeurope"
}

variable "resource_group_name" {
  description = "resource group name"
  type        = string
  default = "eventhubutils-rg"
}


variable "resource_tags" {
  description = "Tags to set for all resources"
  type        = map(string)
  default     = {
    project     = "eventhubutils",
    environment = "dev"
  }
}


