module Items
  class ItemDestroyerService < BaseService
    attr_reader :user, :item_id

    def call
      item = Item.find(resource_id)

      authorize!(ItemPolicy, :destroy?, item)

      item.destroy!
      nil
    end
  end
end
