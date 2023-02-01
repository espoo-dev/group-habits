module Categories
  class CategoryCreatorService < BaseService
    attr_reader :user, :name

    def initialize(user:, create_category_params:)
      @user = user
      @name = create_category_params[:name]
    end

    def call
      category = Category.new(user:, name:)

      authorize!(CategoryPolicy, :create?, category)

      category.save!
      category
    end
  end
end
