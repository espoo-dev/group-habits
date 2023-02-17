module Items
  class ItemsFinderService < BaseService
    attr_reader :name, :item_type

    def initialize(user:, params:)
      @user = user
      @name = params[:name]
      @item_type = params[:item_type]
    end

    def call
      items = ItemPolicy::Scope.new(user, Item).resolve
                               .by_name_like(name)
                               .by_item_type(item_type)
                               .includes(%i[category sales_unit])

      authorize!(ItemPolicy, :index?, items)
    end
  end
end
