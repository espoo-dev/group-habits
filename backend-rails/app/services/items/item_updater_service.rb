module Items
  class ItemUpdaterService < BaseService
    attr_reader :user, :item, :id, :update_item_params

    def initialize(user:, update_item_params:)
      @user = user
      @id = update_item_params[:id]
      @update_item_params = update_item_params
    end

    def call
      item = Item.find(id)

      authorize!(ItemPolicy, :update?, item)

      item.update!(update_item_params)
      item
    end
  end
end
