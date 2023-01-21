class CategoriesFinderService < BaseService
  attr_reader :user, :name

  def initialize(user:, find_params:)
    @user = user
    @name = find_params[:name]
  end

  def call
    categories = CategoryPolicy::Scope.new(user, Category).resolve
                                      .by_name_like(name)

    authorize!(CategoryPolicy, :index?, categories)

    categories
  end
end
