class CategoryUpdaterService < BaseService
  attr_reader :user, :id, :name

  def initialize(user:, update_category_params:)
    @user = user
    @id = update_category_params[:id]
    @name = update_category_params[:name]
  end

  def call
    category = Category.find(id)

    authorize!(CategoryPolicy, :update?, category)

    category.update!(name:)
    category
  end
end
