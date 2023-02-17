module Categories
  class CategoryDestroyerService < BaseService
    attr_reader :category_id

    def call
      category = Category.find(resource_id)

      authorize!(CategoryPolicy, :destroy?, category)

      category.destroy!
      nil
    end
  end
end
