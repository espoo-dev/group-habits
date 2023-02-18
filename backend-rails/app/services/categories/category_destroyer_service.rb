module Categories
  class CategoryDestroyerService < DestroyerService
    def prepare_resource
      Category.find(resource_id)
    end
  end
end
