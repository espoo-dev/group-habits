module Categories
  class CategoriesFinderService < BaseService
    attr_reader :name

    def initialize(user:, params:)
      @user = user
      @name = params[:name]
    end

    def call
      categories = CategoryPolicy::Scope.new(user, Category).resolve
                                        .by_name_like(name)

      authorize!(CategoryPolicy, :index?, categories)
    end
  end
end
