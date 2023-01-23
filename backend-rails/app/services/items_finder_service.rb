class ItemsFinderService < BaseService
  attr_reader :user, :name, :item_type

  def initialize(user:, index_params:)
    @user = user
    @name = index_params[:name]
    @item_type = index_params[:item_type]
  end

  def call
    items = ItemPolicy::Scope.new(user, Item).resolve
                             .by_name_like(name)
                             .by_item_type(item_type)

    authorize!(ItemPolicy, :index?, items)

    items
  end
end
