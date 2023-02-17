module Categories
  class CategoryCreatorService < BaseService
    attr_reader :create_category_params

    def initialize(user:, create_category_params:)
      @user = user
      @create_category_params = create_category_params.merge(user:)
    end

    def call
      category = Category.new(create_category_params)

      authorize!(CategoryPolicy, :create?, category)

      category.save!
      category
    end
  end
end
