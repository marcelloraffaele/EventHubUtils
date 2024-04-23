
data "azurerm_resource_group" "rg" {
  name = var.resource_group_name
}

//https://registry.terraform.io/providers/hashicorp/azurerm/latest/docs/resources/eventhub.html

resource "azurerm_eventhub_namespace" "eh_namespace" {
  name                = "acceptanceTestEventHubNamespace"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  sku                 = "Standard"
  capacity            = 1

  tags = var.resource_tags
}

resource "azurerm_eventhub" "eh" {
  name                = "acceptanceTestEventHub"
  namespace_name      = azurerm_eventhub_namespace.eh_namespace.name
  resource_group_name = azurerm_resource_group.rg.name
  partition_count     = 1
  message_retention   = 1
}

//https://registry.terraform.io/providers/hashicorp/azurerm/latest/docs/resources/storage_container