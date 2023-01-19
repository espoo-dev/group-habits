class CategoryDestroyerService
  attr_reader :user, :category_id

  def initialize(user:, destroy_category_params:)
    @user = user
    @category_id = destroy_category_params[:id]
  end

  def call
    category = Category.find(category_id)

    authorize!(user:, category:)

    category.destroy!
    nil
  end

  private

  def authorize!(user:, category:)
    raise Pundit::NotAuthorizedError unless CategoryPolicy.new(user, category).destroy?
  end
end
