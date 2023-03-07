module ServiceOrders
  class ServiceOrderUpdaterService < UpdaterService
    def prepare_resource
      service_order = ServiceOrder.find(resource_id)
      service_order.items = items
      service_order
    end

    private

    def items
      items_ids = params_with_user[:items_ids]
      items = items_ids&.map { Item.find(_1) }
      items || []
    end

    def call_action
      resource.update!(params.except(:items_ids))
      resource
    end
  end
end
