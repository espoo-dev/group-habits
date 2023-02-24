module ServiceOrders
  class ServiceOrderDestroyerService < DestroyerService
    def prepare_resource
      ServiceOrder.find(resource_id)
    end
  end
end
