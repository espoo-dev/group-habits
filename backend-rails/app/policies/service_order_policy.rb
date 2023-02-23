class ServiceOrderPolicy < ApplicationPolicy
  class Scope < ApplicationPolicy::Scope
    def resolve
      ServiceOrder.where(user:)
    end
  end
end
