module Categories
  class CategoryDestroyerService < DestroyerService

    def prepare_resource
      resource = Category.find(resource_id)
    end
  end
end
