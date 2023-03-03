module ServiceOrders
  class ServiceOrderCreatorService < CreatorService
    def prepare_resource
      params[:creation_date] = DateUtil.parse_date(params[:creation_date])
      params[:conclusion_date] = DateUtil.parse_date(params[:conclusion_date])
      service_order = ServiceOrder.new(params_with_user.except(:items_ids))
      service_order.items = items
      service_order
    end

    private

    def items
      items_ids = params_with_user[:items_ids]
      items = items_ids&.map { Item.find(_1) }
      items || []
    end
  end
end
