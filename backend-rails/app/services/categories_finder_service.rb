class CategoriesFinderService
  attr_reader :user

  def initialize(user:)
    @user = user
  end

  def call
    categories = CategoryPolicy::Scope.new(user, Category).resolve

    authorize!(user:, categories:)

    categories
  end

  private

  def authorize!(user:, categories:)
    raise Pundit::NotAuthorizedError unless DailyHabitPolicy.new(user, categories).index?
  end
end
