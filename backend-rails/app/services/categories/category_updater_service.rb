module Categories
  class CategoryUpdaterService < BaseService

    def initialize(user:, params:)
      @user = user
      @params = params
    end

    def call
      category = Category.find(resource_id)

      authorize!(CategoryPolicy, :update?, category)

      category.update!(params)
      category
    end
  end
end
