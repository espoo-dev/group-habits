module Categories
  class CategoryDestroyerService < BaseService

    def call
      category = Category.find(resource_id)

      authorize!(CategoryPolicy, :destroy?, category)

      category.destroy!
      nil
    end
  end
end
