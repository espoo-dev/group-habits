module Items
  class ItemUpdaterService < UpdaterService
    def prepare_resource
      Item.find(resource_id)
    end
  end
end
