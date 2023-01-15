class CategoryCreatorService
  attr_reader :user, :name

  def initialize(user:, create_category_params:)
    @user = user
    @name = create_category_params[:name]
  end

  def call
    category = Category.new(user:, name:)

    authorize!(user:, category:)

    category.save!
    category
  end

  private

  def authorize!(user:, category:)
    raise Pundit::NotAuthorizedError unless CategoryPolicy.new(user, category).create?
  end
end
