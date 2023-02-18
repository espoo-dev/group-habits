module Items
  class ItemUpdaterService < UpdaterService
    def resource_class
      Item
    end
  end
end
