class CategoryPolicy < ApplicationPolicy
  def index?
    true
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
