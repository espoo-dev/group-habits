module Items
  class ItemDestroyerService < DestroyerService
    def prepare_resource
      resource = Item.find(resource_id)
    end
  end
end
