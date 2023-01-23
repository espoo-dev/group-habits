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
      sale_price: item.sale_price,
      purchase_price: item.purchase_price,
      sales_unit: item.sales_unit,
      item_type: item.item_type,
      category_id: item.category_id
    }
  end
end
