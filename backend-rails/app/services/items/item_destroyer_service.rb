module Items
  class ItemDestroyerService < BaseService
    attr_reader :user, :item_id

    def initialize(user:, params:)
      @user = user
      @item_id = params[:id]
    end

    def call
      item = Item.find(item_id)

      authorize!(ItemPolicy, :destroy?, item)

      item.destroy!
      nil
    end
  end
end
