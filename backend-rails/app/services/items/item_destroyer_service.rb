module Items
  class ItemDestroyerService < BaseService
    def call
      item = Item.find(resource_id)

      authorize!(ItemPolicy, :destroy?, item)

      item.destroy!
      nil
    end
  end
end
