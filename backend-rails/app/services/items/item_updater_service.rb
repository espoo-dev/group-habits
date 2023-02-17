module Items
  class ItemUpdaterService < BaseService

    def call
      item = Item.find(resource_id)

      authorize!(ItemPolicy, :update?, item)

      item.update!(params)
      item
    end
  end
end
