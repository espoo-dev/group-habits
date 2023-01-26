class ApplicationPolicy
  attr_reader :user, :record

  def initialize(user, record)
    @user = user
    @record = record
  end

  def index?
    true
  end

  def show?
    false
  end

  def create?
    owner_user?
  end

  def new?
    create?
  end

  def update?
    owner_user?
  end

  def edit?
    update?
  end

  def destroy?
    owner_user?
  end

  def owner_user?
    record.user == user
  end

  class Scope
    attr_reader :user, :scope

    def initialize(user, scope)
      @user = user
      @scope = scope
    end

    def resolve
      scope.all
    end
  end
end
