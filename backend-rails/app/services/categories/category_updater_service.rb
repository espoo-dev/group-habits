module Categories
  class CategoryUpdaterService < BaseService

    def call
      category = Category.find(resource_id)

      authorize!(CategoryPolicy, :update?, category)

      category.update!(params)
      category
    end
  end
end
