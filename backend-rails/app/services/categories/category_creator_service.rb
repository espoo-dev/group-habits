module Categories
  class CategoryCreatorService < BaseService
    def call
      category = Category.new(params_with_user)

      authorize!(CategoryPolicy, :create?, category)

      category.save!
      category
    end
  end
end
