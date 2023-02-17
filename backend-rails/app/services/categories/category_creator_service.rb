module Categories
  class CategoryCreatorService < BaseService
    attr_reader :params

    def initialize(user:, params:)
      @user = user
      @params = params.merge(user:)
    end

    def call
      category = Category.new(params)

      authorize!(CategoryPolicy, :create?, category)

      category.save!
      category
    end
  end
end
