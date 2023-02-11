module Items
  class ItemCreatorService < BaseService
    attr_reader :name, :extra_info, :sale_price, :purchase_price, :item_type, :category_id, :sales_unit_id

    def initialize(user:, create_item_params:)
      @user = user
      @name = create_item_params[:name]
      @extra_info = create_item_params[:extra_info]
      @sale_price = create_item_params[:sale_price]
      @purchase_price = create_item_params[:purchase_price]
      @item_type = create_item_params[:item_type]
      @category_id = create_item_params[:category_id]
      @sales_unit_id = create_item_params[:sales_unit_id]
    end

    def call
      item = Item.new(user:, name:, extra_info:, sale_price:, purchase_price:, item_type:, category_id:, sales_unit_id:)

      authorize!(ItemPolicy, :create?, item)

      item.save!
      item
    end
  end
end
