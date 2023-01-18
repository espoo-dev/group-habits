class CategoryFinderService
  attr_reader :user, :id

  def initialize(user:, id:)
    @user = user
    @id = id
  end

  def call
    category = Category.find_by(id: id, user_id: user.id)
    
    authorize!(user: user, category: category)

    category
  end

  private

  def authorize!(user:, category:)
    raise Pundit::NotAuthorizedError unless CategoryPolicy.new(user, category).update?
  end
end
