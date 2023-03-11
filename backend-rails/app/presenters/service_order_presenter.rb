class ServiceOrderPresenter < BasePresenter
  attr_accessor :service_order

  def initialize(service_order)
    @service_order = service_order

    super()
  end

  def payload
    {
      id: service_order.id,
      extra_info: service_order.extra_info,
      status: service_order.status,
      creation_date: DateUtil.format_date(service_order.creation_date),
      conclusion_date: DateUtil.format_date(service_order.conclusion_date),
      discount: service_order.discount.to_f,
      customer: CustomerPresenter.payload_for_item(service_order.customer),
      items: ItemPresenter.payload_for_list(service_order.items)
    }
  end
end
