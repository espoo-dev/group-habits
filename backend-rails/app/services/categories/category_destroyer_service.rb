module Categories
  class CategoryDestroyerService < BaseService
    attr_reader :user, :category_id

    def initialize(user:, params:)
      @user = user
      @category_id = params[:id]
    end

    def call
      category = Category.find(category_id)

      authorize!(CategoryPolicy, :destroy?, category)

      category.destroy!
      nil
    end
  end
end
