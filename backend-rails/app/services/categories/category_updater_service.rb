module Categories
  class CategoryUpdaterService < UpdaterService
    def prepare_resource
      Category.find(resource_id)
    end
  end
end
