class CategoryPolicy < ApplicationPolicy
  def index?
    true
  end

  def create?
    owner_user?
  end

  class Scope
    attr_reader :user, :scope

    def initialize(user, scope)
      @user = user
      @scope = scope
    end

    def resolve
      Category.where(user:)
    end
  end
end
