module Categories
  class CategoryUpdaterService < BaseService
    attr_reader :id, :update_category_params

    def initialize(user:, update_category_params:)
      @user = user
      @id = update_category_params[:id]
      @update_category_params = update_category_params
    end

    def call
      category = Category.find(id)

      authorize!(CategoryPolicy, :update?, category)

      category.update!(update_category_params)
      category
    end
  end
end
