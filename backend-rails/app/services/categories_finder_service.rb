class CategoriesFinderService
  attr_reader :user, :name

  def initialize(user:, find_params:)
    @user = user
    @name = find_params[:name]
  end

  def call
    categories = CategoryPolicy::Scope.new(user, Category).resolve
                                      .by_name_like(name)

    authorize!(user:, categories:)

    categories
  end

  private

  def authorize!(user:, categories:)
    raise Pundit::NotAuthorizedError unless DailyHabitPolicy.new(user, categories).index?
  end
end
