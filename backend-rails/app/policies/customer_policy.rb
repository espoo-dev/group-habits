class CustomerPolicy < ApplicationPolicy
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
      Customer.where(user:)
    end
  end
end
