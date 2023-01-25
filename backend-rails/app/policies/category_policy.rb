class CategoryPolicy < ApplicationPolicy
  def index?
    true
  end

  def create?
    owner_user?
  end

  def destroy?
    owner_user?
  end

  def update?
    owner_user?
  end

  class Scope < ApplicationPolicy::Scope
    def resolve
      Category.where(user:)
    end
  end
end
