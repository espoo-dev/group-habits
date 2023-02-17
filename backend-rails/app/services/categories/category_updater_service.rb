module Categories
  class CategoryUpdaterService < BaseService
    attr_reader :id, :params

    def initialize(user:, params:)
      @user = user
      @id = params[:id]
      @params = params
    end

    def call
      category = Category.find(id)

      authorize!(CategoryPolicy, :update?, category)

      category.update!(params)
      category
    end
  end
end
