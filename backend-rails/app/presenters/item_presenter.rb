class ItemPresenter < BasePresenter
  attr_accessor :item

  def initialize(item)
    @item = item

    super()
  end

  def payload
    {
      id: item.id,
      name: item.name,
      extra_info: item.extra_info,
      sale_price: item.sale_price.to_f,
      purchase_price: item.purchase_price.to_f,
      item_type: item.item_type,
      category: CategoryPresenter.payload_for_item(item.category),
      sales_unit: SalesUnitPresenter.payload_for_item(item.sales_unit)
    }
  end
end
