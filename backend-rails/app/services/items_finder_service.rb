class ItemsFinderService < BaseService
  attr_reader :user, :name

  def initialize(user:, index_params:)
    @user = user
    @name = index_params[:name]
  end

  def call
    items = ItemPolicy::Scope.new(user, Item).resolve
                             .by_name_like(name)

    authorize!(ItemPolicy, :index?, items)

    items
  end
end
